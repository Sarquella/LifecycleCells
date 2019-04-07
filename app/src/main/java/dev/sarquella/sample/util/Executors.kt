package dev.sarquella.sample.util

import java.util.concurrent.Executors


/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}
 
 