package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;
import android.test.ApplicationTestCase;
import com.udacity.gradle.builditbigger.JokeAsyncTask.TaskCompleteListener;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by aditibhattacharya on 22/03/2018.
 */

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest extends ApplicationTestCase<Application> {


    private CountDownLatch mSignal;
    private static final String JOKE_TYPE = "science";



    public JokeAsyncTaskTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // signal to indicate when task is completed
        mSignal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mSignal.countDown();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testJokeIsFetced() throws Throwable {

        String jokeFetched;

        JokeAsyncTask task = new JokeAsyncTask(new TaskCompleteListener() {
            @Override
            public void onTaskComplete(String result) {
            }
        });

        try {
            task.execute(new Pair<Context, String>(InstrumentationRegistry.getTargetContext(), JOKE_TYPE));
            jokeFetched = task.get();
            assertNotNull("Joke fetched is NULL", jokeFetched);
            assertTrue("Joke fetched is EMPTY", !jokeFetched.isEmpty());
        } catch (InterruptedException ie) {
            fail("Something wrong happened!!");
        }

    }

}