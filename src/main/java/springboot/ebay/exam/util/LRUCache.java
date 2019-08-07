package springboot.ebay.exam.util;

import java.util.HashMap;
import java.util.Map;


public class LRUCache {
     /*
      Our internal definition of a doubly linked list
      node, put in this class for convenience since
      this is submitted in one file on Leetcode
    */
  
    private class DNode {
        String key;
        String value;
        DNode prev;
        DNode next;
      }
  
      private Map<String, DNode> hashtable = new HashMap<String, DNode>();
      private DNode head, tail;
      private int totalItemsInCache;
      private int maxCapacity;
  
      public LRUCache(int maxCapacity) {
  
        totalItemsInCache = 0;
        this.maxCapacity = maxCapacity;
  
        head = new DNode();
        head.prev = null;
  
        tail = new DNode();
        tail.next = null;
  
        head.next = tail;
        tail.prev = head;
      }


  
      /*
        Retrieve an item from the cache
      */
      public String get(String key) {
  
        DNode node = hashtable.get(key);
        boolean itemFoundInCache = node != null;
  
        if(!itemFoundInCache){ //item not found
          return null;
        }
  
        moveToHead(node);
  
        return node.value;
      }
  
    /*
      insert new item to cache, if full remove the tail and insert new item to head
    */ 
      public void put(String key, String value) {
  
        DNode node = hashtable.get(key);
        boolean itemFoundInCache = node != null;
  
        if(!itemFoundInCache){
  
          DNode newNode = new DNode();
          newNode.key = key;
          newNode.value = value;
  
          hashtable.put(key, newNode);
          addNode(newNode);
  
          totalItemsInCache++;
  
          if(totalItemsInCache > maxCapacity){
            removeLRUEntryFromStructure();
          }
  
        } else {
          node.value = value;
          moveToHead(node);
        }
  
      }
  
    
      private void removeLRUEntryFromStructure() {
        DNode tail = popTail();
        hashtable.remove(tail.key);
        --totalItemsInCache;
      }
  
     
      private void addNode(DNode node){
  
        node.prev = head;
        node.next = head.next;
  
        head.next.prev = node;
  
        head.next = node;
      }
  

      private void removeNode(DNode node){
  
        DNode savedPrev = node.prev;
        DNode savedNext = node.next;
  
        savedPrev.next = savedNext;
  
        savedNext.prev = savedPrev;
      }
  
    
      private void moveToHead(DNode node){
        removeNode(node);
        addNode(node);
      }
  
    
      private DNode popTail(){
        DNode itemBeingRemoved = tail.prev;
        removeNode(itemBeingRemoved);
        return itemBeingRemoved;
      }

}