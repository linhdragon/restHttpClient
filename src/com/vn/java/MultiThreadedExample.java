package com.vn.java;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * An example that performs GETs from multiple threads.
 *
 * @author Michael Becke
 */
public class MultiThreadedExample {
    
    /**
     * Constructor for MultiThreadedExample.
     */
    public MultiThreadedExample() {
        super();
    }
    
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient(
                new MultiThreadedHttpConnectionManager());
        
        httpClient.getHostConfiguration().setHost(
                "jakarta.apache.org", 80, "http");
        
        // create an array of URIs to perform GETs on
        String[] urisToGet = {
            "/",
            "http://www.java2s.com/Code/Jar/c/Downloadcommonshttpclient202jar.htm",
            "http://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.1.1",
            "http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-apache-httpclient/",
            "http://www.java-tips.org/other-api-tips/httpclient/how-to-use-multiple-threads-for-performing-get-req.html",
            "https://www.google.com.vn/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=git+extension+ubuntu",
            "http://www.tutorialspoint.com/bootstrap/bootstrap_forms.htm",
            "http://www.tutorialspoint.com/bootstrap/bootstrap_code.htm",
            "http://www.tutorialspoint.com/bootstrap/bootstrap_tables.htm",
            "http://www.tutorialspoint.com/bootstrap/bootstrap_buttons.htm",
            "http://www.tutorialspoint.com/bootstrap/bootstrap_images.htm"
        };
        
        // create a thread for each URI
        GetThread[] threads = new GetThread[urisToGet.length];
       
        for (int i = 0; i < threads.length; i++) {
            GetMethod get = new GetMethod(urisToGet[i]);
            get.setFollowRedirects(true);
            threads[i] = new GetThread(httpClient, get, i + 1);
        }
        try {
        	 // start the threads
            for (int j = 0; j < threads.length; j++) {
                threads[j].start();
            }
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
		}
    }
    
    /**
     * A thread that performs a GET.
     */
    static class GetThread extends Thread {
        
        private HttpClient httpClient;
        private GetMethod method;
        private int id;
        
        public GetThread(HttpClient httpClient, GetMethod method, 
                int id) {
            
            this.httpClient = httpClient;
            this.method = method;
            this.id = id;
            
        }
        
        /**
         * Executes the GetMethod and prints some satus information.
         */
        public void run() {
            
            try {
                
                System.out.println(id + 
                        " - about to get something from " + 
                        method.getURI());
                
                // execute the method
                httpClient.executeMethod(method);
                
                System.out.println(id + " - get executed");
                
                // get the response body as an array of bytes
                byte[] bytes = method.getResponseBody();
                
                System.out.println(id + " - " + bytes.length + 
                        " bytes read");
                
            } catch (Exception e) {
                System.out.println(id + " - error: " + e);
            } finally {
                // always release the connection after we're done
                method.releaseConnection();
                System.out.println(id + " - connection released");
            }
        }
        
    }
    
}