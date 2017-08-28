package com.lxp.util.array;

import org.junit.Test;

import java.util.*;

public class PrrayList<T> implements List<T> {

    //声明一个对象数组来存放对象
    private Object[] elementData;

    //list大小
    private int size;

    public boolean isEmpty() {
        return size==0;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator<T> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public T set(int index, T element) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<T> listIterator() {
        return null;
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        return "PrrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }



    public void  ensureSize(int minSize){
        if(minSize>0){
            ensureSizeinner(minSize);
        }

    }

    //内部使用的确保数组大小够用不越界
    private void ensureSizeinner(int minSize){
        if(minSize-elementData.length>0)
            grow(minSize);
    }

    //为数组扩容
    private void grow(int minSize){
        int oldSize=elementData.length;
        int newSize=oldSize+(oldSize>>1);
        if(newSize-minSize<0)
            newSize=minSize;

        //System.out.println("扩容一次,扩容后数组大小为:"+newSize);
        elementData=Arrays.copyOf(elementData,newSize);
    }



    public PrrayList() {
        this(10);
    }

    public PrrayList(int size){
        if(size<0)
            throw new IllegalArgumentException("size 不能小于零");
        this.elementData=new Object[10];
    }

    public int size() {
        return size;
    }

    public boolean add(T t) {
        ensureSizeinner(size+1);
        elementData[size++]=t;//==>等价于elementData[size]=t;size++;
        return true;
    }

    public T get(int index) {
        RangeCheck(index);
        return getElementData(index);
    }

    private T getElementData(int index){
        return (T) elementData[index];
    }

    //数组范围越界检查
    private void RangeCheck(int index) {
        if(index<0 || index>=size)
            throw new IndexOutOfBoundsException("超出范围异常:"+index);
    }

    public void clear() {
        for(int i=0;i<elementData.length;i++)
            elementData[i]=null;
        size=0;

    }

    //返回移除对象
    public T remove(int index) {
        RangeCheck(index);

        T OldValue= (T) elementData[index];

        //需要移动的index后边的数组的长度
        int numMove=size-index-1;

        //如果是最后一位不需要移动
        if(numMove>0)
            System.arraycopy(elementData,index+1,elementData,index,numMove);


        elementData[--size]=null;//==>elementData[size-1]=null;size--;


        return OldValue;
    }

    public boolean remove(Object o) {
        if(o==null){
            for(int i=0;i<size;i++){
                if(elementData[i]==null){
                    removeFast(i);
                    return true;
                }
            }
        }else{
            for(int i=0;i<size;i++){
                if(o.equals(elementData[i])){//不用elementData[i].equals(o)的原因是数组中有可能有null，会报空指针异常。o已经判断不为null了；
                    removeFast(i);
                    return true;
                }
            }
        }

        return false;
    }

    //移除数组中的对象不返回数组,不对外开放，所以不用数组范围检查。
    private void removeFast(int index){
        int numMove=size-index-1;

        if(numMove>0)
            System.arraycopy(elementData,index+1,elementData,index,numMove);

        elementData[--size]=null;

    }

    public void add(int index, T element) {
        //RangeCheck(index);
        AddRangeCheck(index);
        ensureSizeinner(size+1);
        int numMove=size-index;
        if(numMove>0)
            System.arraycopy(elementData,index,elementData,index+1,numMove);
        elementData[index]=element;
        size++;
    }

    private void AddRangeCheck(int index){
        if(index<0 || index>size)
            throw new IndexOutOfBoundsException("数组越界异常:"+index);
    }

    public static void main (String[] str){
        PrrayList<String> list=new PrrayList<String>();
        for(int i=0;i<5;i++){
            list.add("李小朋["+i+"]");
        }
        System.out.println("list为:"+list+",\nlist大小为:"+list.size());
        list.add(5,"贾欣琪");
        System.out.println("list为:"+list+",\nlist大小为:"+list.size());
    }
}
