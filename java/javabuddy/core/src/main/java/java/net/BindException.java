/*
 * @(#)BindException.java	1.14 05/11/17
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package java.net;

/**
 * Signals that an error occurred while attempting to bind a
 * socket to a local address and port.  Typically, the port is
 * in use, or the requested local address could not be assigned.
 *
 * @since   JDK1.1
 */

public class BindException extends SocketException {

    /**
     * Constructs a new BindException with the specified detail 
     * message as to why the bind error occurred.
     * A detail message is a String that gives a specific 
     * description of this error.
     * @param msg the detail message
     */
    public BindException(String msg) {
	super(msg);
    }

    /**
     * Construct a new BindException with no detailed message.
     */
    public BindException() {}
}
