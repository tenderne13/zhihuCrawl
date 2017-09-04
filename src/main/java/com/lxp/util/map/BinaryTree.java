package com.lxp.util.map;


import com.lxp.util.array.PlinkedList;

import java.util.List;

public class BinaryTree {

    //定义一个根节点；
    private Node root;

    private int size=0;

    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    //插入节点方法
    public void insertNode(int data){
        Node newNode=new Node(data);
        if(root==null){
            root=newNode;
            size++;
            return;
        }else{
            Node current=root;
            while (true){
                if(data<current.data){
                    if(current.left==null){
                        current.left=newNode;
                        size++;
                        return;
                    }else{
                        current=current.left;
                    }
                }else {
                    if(current.right==null){
                        current.right=newNode;
                        size++;
                        return;
                    }else{
                        current=current.right;
                    }
                }
            }
        }

    }


    //前序遍历
    public void preOrder(Node node){
        if(node!=null){
            System.out.print(node.data+"-->");
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    public static void  main(String[] ar){
        BinaryTree tree=new BinaryTree();
        tree.insertNode(80);
        tree.insertNode(37);
        tree.insertNode(50);
        tree.insertNode(100);
        tree.insertNode(40);
        tree.preOrder(tree.root);

    }




}
