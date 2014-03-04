package com.photon.phresco.Listener;

import org.testng.annotations.Test;

public class ThreadLocalDemo {
	@Test
    public void testMethod1() {
        invokeBrowser("https://www.google.com");
    }

    @Test
    public void testMethod2() {
        invokeBrowser("https://www.facebook.com");

    }

    private void invokeBrowser(String url) {
        System.out.println("Thread id = " + Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + LocalDriverManager.getDriver().hashCode());
        LocalDriverManager.getDriver().get(url);

    }
}
