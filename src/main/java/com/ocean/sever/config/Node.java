/**
 * @(#)Node.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.config;

/**
 * @author back
 */
public class Node<T> {
    public long userId;
    public T thread;
    public Node leftChild;
    public Node rightChild;

    public Node(long id, T thread) {
        this.userId = id;
        this.thread = thread;
    }
}