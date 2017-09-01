package com.lxp.util.array;

import com.lxp.vo.Person;

import java.io.IOException;
import java.util.*;

public class PlinkedList<E> implements List<E>,Deque<E>{

    private Node<E> first;
    private Node<E> last;
    private int size;

    //node类，内部使用
    private static class Node<E>{
        private Node<E> prev;
        private E e;
        private Node<E> next;

        public Node(Node<E> prev,E e,Node<E> next) {
            this.prev=prev;
            this.e=e;
            this.next=next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    '}';
        }
    }


    @Override
    public boolean add(E e) {
        linkedLast(e);
        return true;
    }

    private void linkedLast(E e){
        Node<E> oldLast=last;
        //将新节点的前驱节点指向last
        Node<E> newNode=new Node<E>(oldLast,e,null);
        //last指向最新节点
        last=newNode;
        //如果last为null说明linkedlist还未放值，即这个新节点既是fist又是last
        if(oldLast==null){
            first=newNode;
        }else{
            oldLast.next=newNode;
        }
        size++;
    }



    public static void main(String[] arc){
       List<String> list= new PlinkedList<String>();
       list.add("1");
       list.add("2");
       list.add("3");
       System.out.println(list);
    }

    public static int indexFor(int h,int length) throws IOException{
        return h^(length-1);
    }

    @Override
    public void addFirst(E e) {

    }

    @Override
    public void addLast(E e) {

    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        StringBuffer buffer=new StringBuffer();
        buffer.append("List{");
        Node<E> node=first;
        for(int i=0;i<size;i++){
            if(node!=null){
                buffer.append(node.e+",");
            }

            node=node.next;

        }
        buffer.append("}");
        return buffer.toString();
    }
}
