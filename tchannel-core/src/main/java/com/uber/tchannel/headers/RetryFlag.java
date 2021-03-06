/*
 * Copyright (c) 2015 Uber Technologies, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.uber.tchannel.headers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public enum RetryFlag {
    NoRetry('n'),
    RetryOnConnectionError('c'),
    RetryOnTimeout('t');

    private final char flag;

    RetryFlag(char flag) {
        this.flag = flag;
    }

    public static @Nullable RetryFlag toRetryFlag(char c) {
        switch (c) {
            case 'n':
                return NoRetry;
            case 'c':
                return RetryOnConnectionError;
            case 't':
                return RetryOnTimeout;
            default:
                return null;

        }
    }

    public static @NotNull Set<RetryFlag> parseFlags(@NotNull String flags) {
        Set<RetryFlag> retryFlags = new HashSet<>();
        for (char c : flags.toCharArray()) {
            retryFlags.add(toRetryFlag(c));
        }
        return retryFlags;
    }

    public static @NotNull String flagsToString(@NotNull Set<RetryFlag> flags) {
        StringBuilder sb = new StringBuilder();
        for (RetryFlag flag : flags) {
            sb.append(flag.toString());
        }
        return sb.toString();
    }

    public static boolean validFlags(@NotNull String flags) {
        switch (flags) {
            case "c":
            case "t":
            case "n":
            case "ct":
            case "tc":
                return true;

            default:
                return false;
        }
    }

    public char getFlag() {
        return flag;
    }

}
