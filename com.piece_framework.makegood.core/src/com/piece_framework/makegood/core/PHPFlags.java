/**
 * Copyright (c) 2010-2011 KUBO Atsuhiro <kubo@iteman.jp>,
 * All rights reserved.
 *
 * This file is part of MakeGood.
 *
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package com.piece_framework.makegood.core;

import org.eclipse.dltk.core.Flags;

/**
 * @since 1.2.0
 */
public class PHPFlags {
    public static boolean isClass(int flags) {
        return org.eclipse.php.core.compiler.PHPFlags.isClass(flags);
    }

    public static boolean isNamespace(int flags) {
        return org.eclipse.php.core.compiler.PHPFlags.isNamespace(flags);
    }

    /**
     * @since 1.3.0
     */
    public static boolean isAbstract(int flags) {
        return Flags.isAbstract(flags);
    }
}
