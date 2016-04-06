package servlets;

import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("serial")
//@WebServlet("/Display")
public class MJPG extends HttpServlet {
	
//	private final List<byte[]> imageByteList;
	private int currentIndex;
       
    /**
     * Constructor
     * @see HttpServlet#HttpServlet()
     */
    public MJPG() {
        super();
        
        // set the index
        currentIndex = 0;
        
        // load images from the user's home directory into the list of image bytes
//        String[] names = {"summer", "fall", "winter", "spring"};
////        imageByteList = new ArrayList<byte[]>(0);
//
//        for(String name : names) {
//        	try {
//                String str = "img/"+ name + ".jpg";
////        		File image = new File(System.getProperty("user.home") + File.separator
////        				+ name + ".jpg");
//                File image = new File(str);
//        		BufferedImage originalImage = ImageIO.read(image);
//            	ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            	ImageIO.write( originalImage, "jpg", baos );
//            	baos.flush();
////            	imageByteList.add(baos.toByteArray());
//            	baos.close();
//        	} catch (Exception ex) {
//            	System.err.println("There was a problem loading the images.");
//            }
//        }

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

//        PrintWriter out = response.getWriter();
//        String resp = out.toString();


        ArrayList<String> ar = new ArrayList<String>();
        String[] names = {"mini.jpg","mini2.jpg","mini3.jpg"};

        String param = new String(request.getParameter("input_2"));

        String eqparam = "link1";
        if (param.equals("link1")) {
            ar.add("mini.jpg");
            ar.add("mini2.jpg");
            ar.add("mini3.jpg");
        }
        else {
            ar.add("city1.jpg");
            ar.add("city2.jpg");
            ar.add("city3.jpg");
        }

        String req = request.getHeader("User-Agent");
        response.setContentType("text/html;charset=utf-8");



        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("message", "mini2.jpg");
        response.setStatus(HttpServletResponse.SC_OK);

        while(true) {

            try {
                if(currentIndex > 1) currentIndex = 0;
				else currentIndex++;

                pageVariables.put("message", ar.get(currentIndex));
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.print(PageGenerator.instance().getPage("withImage.html", pageVariables));
                outputStream.flush();
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//		response.getWriter().println("Abrakadabra");

//		// get the output stream to write to
//		OutputStream outputStream = response.getOutputStream();
//
//		// loop over and send the images while the browser is present and listening, then return
//		while(true) {
//			try {
//				// reset the current index if necessary or increment it
//				if(currentIndex > 2) currentIndex = 0;
//				else currentIndex++;
//
//				// write the image and wrapper
//				outputStream.write((
//					"--BoundaryString\r\n" +
//					"Content-type: image/jpeg\r\n" +
//					"Content-Length: " +
//					imageByteList.get(currentIndex).length +
//					"\r\n\r\n").getBytes());
//				outputStream.write(imageByteList.get(currentIndex));
////				outputStream.write("\r\n\r\n".getBytes());
////				outputStream.flush();
//
//				// force sleep to not overwhelm the browser, simulate ~20 FPS
//				TimeUnit.MILLISECONDS.sleep(500);
//			}
//
//			// If there is a problem with the connection (it likely closed), so return
//			catch (Exception e) {
//				return;
//			}
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// direct post requests to the get method
		doGet(request, response);
		
	}



    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
