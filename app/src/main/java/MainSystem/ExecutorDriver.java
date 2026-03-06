
package MainSystem;

import ConsoleSystem.Console;
import ConsoleSystem.ConsoleColors;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorDriver{

    private static final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    
    public static void closeExecutor(){
        Console.line().out("CLOSING EXECUTOR", ConsoleColors.GREEN);
        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)){
                executorService.shutdownNow();
            } 
        }catch(InterruptedException e){
            executorService.shutdownNow();
        }
        Console.out("Executor closed");
    }
    
    public static <T> Future<T> submit(Callable<T> task){
        return executorService.submit(task);
    }
    
    public static void execute(Runnable command){
        executorService.execute(command);
    }

}
