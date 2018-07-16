package com.qingtian.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/07/09
 * @description 单链表
 */
public class SinglyLinkedList {

    public static final Logger log = LoggerFactory.getLogger(SinglyLinkedList.class);

    private Node first = null;
    private Node last = null;

    /**
     * 构造函数，初始化头结点
     */
    public SinglyLinkedList() {
        first = new Node<>("A");
    }

    /**
     * 创建一条单链表
     * A-B-C-D-E-F-G
     */
    public void createLinkedList() {

        if (first == null) {
            first = new Node<>("A");
        }

        Node B = new Node<>("B");
        Node C = new Node<>("C");
        Node D = new Node<>("D");
        Node E = new Node<>("E");
        Node F = new Node<>("F");
        Node G = new Node<>("G");

        first.next = B;
        B.next = C;
        C.next = D;
        D.next = E;
        E.next = F;
        F.next = G;
        last = G;
    }

    /**
     * 打印头节点为first的链表
     *
     * @param first
     */
    public void print(Node first) {
        Node current = first;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * 清空一个链表
     */
    public void clear() {
        this.first = null;
    }

    /**
     * 在链表的末尾插入一个节点
     *
     * @param data 节点数据
     */
    public void insert(String data) {
        Node temp = new Node(data);
        // 链表为空，在头节点插入
        if (this.isEmpty()) {
            first = temp;
            last = temp;
        } else {// 在链表末尾插入
            last.next = temp;
            last = temp;
        }
    }

    /**
     * 在指定的节点后插入一个新的节点
     *
     * @param node
     * @param data
     */
    public void insert(Node node, String data) {

        Node insert = new Node<>(data);

        // 链表为空，在头节点插入
        if (this.isEmpty()) {
            first = insert;
            last = insert;
        } else {
            // 在链表末尾插入
            if (last.data.equals(node.data)) {
                last.next = insert;
                last = insert;
            } else {// 中间结点插入
                insert.next = node.next;
                node.next = insert;
            }
        }
    }

    /**
     * 删除指定的节点
     *
     * @param node 需要删除的节点
     */
    public void delete(Node node) {

        // 删除头节点
        if (first.data.equals(node.data)) {
            first = first.next;
        } else if (last.data.equals(node.data)) {
            // 删除末节点
            Node current = first;
            Node temp = null;
            while (!current.data.equals(node.data)) {
                temp = current;
                current = current.next;
            }
            temp.next = null;
            last = temp;
        } else {//删除中间节点
            Node current = first;
            Node temp = null;
            while (!current.data.equals(node.data)) {
                temp = current;
                current = current.next;
            }
            temp.next = node.next;
        }
    }

    /**
     * 返回指定节点
     *
     * @param data
     * @return
     */
    public Node get(String data) {
        Node current = first;
        Node temp = null;
        while (!current.data.equals(data)) {
            temp = current;
            current = current.next;
        }
        // 当前需要返回的指定节点
        Node newNode = temp.next;
        return newNode == null ? new Node<>("-1") : newNode;
    }

    /**
     * 反转一个单链表
     *
     * @param first 头结点
     * @author mcrwayfun
     * @version 1.0
     * @date 2018/7/11
     */
    public void reverse(Node first) {

        Node current = first;
        Node before = null;
        while (current != null) {
            Node temp = before;
            before = current;
            current = current.next;
            before.next = temp;
        }
        this.first = before;
        this.last = first;
    }

    /**
     * 串联两个单链表
     *
     * @param head1 单链表1
     * @param head2 单链表2
     * @return
     * @author mcrwayfun
     * @version 1.0
     * @date 2018/7/11
     */
    public Node concatenate(Node head1, Node head2) {

        Node current = head1;
        Node last = null;
        while (current != null) {
            last = current;
            current = current.next;
        }
        last.next = head2;

        return head1;
    }

    /**
     * Node类，用于初始化一个结点
     *
     * @param <T>
     */
    private static class Node<T> {

        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

    }


    public static void main(String[] args) {

        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.createLinkedList();

        log.info("----------------- 链表是否为空 ----------------------");
        boolean empty = singlyLinkedList.isEmpty();
        log.info("empty={}", empty);

        log.info("----------------- 打印链表 ----------------------");
        singlyLinkedList.print(singlyLinkedList.first);

        log.info("----------------- 在链表末尾插入一个节点 ----------------------");
        singlyLinkedList.insert("H");
        singlyLinkedList.print(singlyLinkedList.first);

        log.info("----------------- 删除指定节点 ----------------------");
        Node D = singlyLinkedList.get("D");
        singlyLinkedList.delete(D);
        singlyLinkedList.print(singlyLinkedList.first);

        log.info("----------------- 在链表指定位置插入一个节点 ----------------------");
        singlyLinkedList.clear();
        singlyLinkedList.createLinkedList();
        Node C = singlyLinkedList.get("C");
        singlyLinkedList.insert(C, "I");
        singlyLinkedList.print(singlyLinkedList.first);

        log.info("----------------- 翻转链表 ----------------------");
        singlyLinkedList.clear();
        singlyLinkedList.createLinkedList();
        singlyLinkedList.reverse(singlyLinkedList.first);
        singlyLinkedList.print(singlyLinkedList.first);

        log.info("----------------- 串联链表 ----------------------");
        singlyLinkedList.clear();
        singlyLinkedList.createLinkedList();

        SinglyLinkedList singlyLinkedList1 = new SinglyLinkedList();
        singlyLinkedList1.createLinkedList();

        Node concatenate = singlyLinkedList.concatenate(singlyLinkedList.first, singlyLinkedList1.first);
        singlyLinkedList.print(concatenate);

        List a = new LinkedList();
    }
}