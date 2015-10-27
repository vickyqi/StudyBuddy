/*
 * @(#)WindowsPreferencesFactory.java	1.8 05/11/17
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package java.util.prefs;

/**
 * Implementation of  <tt>PreferencesFactory</tt> to return 
 * WindowsPreferences objects.
 *
 * @author  Konstantin Kladko
 * @version 1.8, 11/17/05
 * @see Preferences
 * @see WindowsPreferences
 * @since 1.4
 */
class WindowsPreferencesFactory implements PreferencesFactory  {
    
    /**
     * Returns WindowsPreferences.userRoot
     */
    public Preferences userRoot() {
        return WindowsPreferences.userRoot;
    }
    
    /**
     * Returns WindowsPreferences.systemRoot
     */
    public Preferences systemRoot() {
        return WindowsPreferences.systemRoot;
    }
}
