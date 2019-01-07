package net.explodingbush.ksoftapi;

import java.util.concurrent.*;
import java.util.function.Consumer;

public abstract class KSoftActionAdapter<T> implements KSoftAction<T> {

    /**
     * Executes the provided request asynchronously. This will not return any consumer.
     */
    @Override
    public void executeAsync() {
       this.executeAsync((result) -> {});
    }

    /**
     * Executes the provided request asynchronously.
     * This will ignore all called exceptions.
     *
     * <p><b>This method is asynchronous</b>
     *
     * @param success
     * The success callback that will be called at at a convenient time for the wrapper. (this can be null)
     *
     */
    @Override
    public void executeAsync(Consumer<? super T> success) {
    	this.executeAsync(success, (e) -> {
    		System.err.println("An exception occured while making a request");
    		e.printStackTrace();
    	});
    }
    /**
     * Executes the provided request asynchronously.
     *
     * <p><b>This method is asynchronous</b>
     *
     * @param success
     * The success callback that will be called at at a convenient time for the wrapper. (this can be null)
     * @param  failure
     * The failure callback that will be called if the execution encounters an exception.
     *
     */
    @Override
    public void executeAsync(Consumer<? super T> success, Consumer<? super Throwable> failure) {
        CompletableFuture.supplyAsync(this::execute)
        		.whenCompleteAsync((result, e) -> {
        			try {
            			if(e == null) {
            				success.accept(result);
            			}
            			else {
            				failure.accept(e);
            			}
        			}
        			catch(Exception exception) {
                 		System.err.println("A consumer threw an exception");
                		exception.printStackTrace();
        			}
        		});
    }
}
