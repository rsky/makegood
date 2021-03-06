/**
 * Copyright (c) 2009-2010 MATSUFUJI Hideharu <matsufuji2008@gmail.com>,
 *               2010 KUBO Atsuhiro <kubo@iteman.jp>,
 * All rights reserved.
 *
 * This file is part of MakeGood.
 *
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package com.piece_framework.makegood.core.result;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestSuiteResult extends Result {
    private String fullPackageName;
    private String packageName;
    private List<Result> children = new CopyOnWriteArrayList<Result>();
    private int allTestCount;

    public TestSuiteResult(String name) {
        super(name);
    }

    public String getFullPackageName() {
        return fullPackageName;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public int getTestCount() {
        int count = 0;
        for (Result result : children) {
            count += result.getTestCount();
        }

        return count;
    }

    @Override
    public int getErrorCount() {
        int count = 0;
        for (Result result : children) {
            count += result.getErrorCount();
        }

        return count;
    }

    @Override
    public int getFailureCount() {
        int count = 0;
        for (Result result : children) {
            count += result.getFailureCount();
        }

        return count;
    }

    @Override
    public void addChild(Result result) {
        if (result == null) {
            return;
        }

        result.setParent(this);
        children.add(result);
    }

    @Override
    public List<Result> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public void setTime(long time) {
        super.setTime(getTime() + time);
        if (getParent() != null) {
            getParent().setTime(time);
        }
    }

    public void setFullPackageName(String fullPackage) {
        this.fullPackageName = fullPackage;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setAllTestCount(int allTestCount) {
        this.allTestCount = allTestCount;
    }

    public int getAllTestCount() {
        return allTestCount;
    }

    public int getPassCount() {
        return getTestCount() - (getFailureCount() + getErrorCount());
    }

    @Override
    public boolean isFixed() {
        for (Result result : children) {
            if (result.isFixed()) return true;
        }

        return false;
    }

    @Override
    public boolean hasChildren() {
        return children.size() > 0;
    }

    @Override
    public int getSize() {
        int count = 1;
        for (Result result : children) {
            count += result.getSize();
        }

        return count;
    }
}
