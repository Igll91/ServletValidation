package silvio.vuk.ag04.validator.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import silvio.vuk.java.ag04.validator.ValidationControlCenter;

/**
 * Servlet implementation class ValidatorServlet
 */
@WebServlet("/Validator.html")
public class ValidatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final String UPLOAD_DIRECTORY = "/var/tmp/";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/jsp/Index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request))
        {
            try 
            {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts)
                {
                    if(!item.isFormField())
                    {
                        String name = new File(item.getName()).getName();
                        File uploadedItem = new File(UPLOAD_DIRECTORY + File.separator + name);
                        item.write( uploadedItem);
                        
                        String path = uploadedItem.getAbsolutePath();
                        
                        ValidationControlCenter vcc = new ValidationControlCenter(path);
                        
                        try
                        {
                        	vcc.validateFile();
                        	
                        	String filePathForReading = path.substring(0, path.lastIndexOf("/"));
                        	filePathForReading +="/ValidationResults.txt";
                        	
                        	List<String> listOfStrings = readValidationFile(filePathForReading);
                        	
                        	request.setAttribute("results", listOfStrings);
                        	
                        	 //File uploaded successfully
                            request.setAttribute("message", "File validated Successfully!");
                        }
                        catch(FileNotFoundException ex)
                        {
                        	request.setAttribute("message", "File validation Failed due to " + ex);
                        }
                        catch(InvalidPropertiesFormatException ex)
                        {
                        	request.setAttribute("message", "File validation Failed due to " + ex);
                        }
                        catch(IOException ex)
                        {
                        	request.setAttribute("message", "File validation Failed due to " + ex);
                        }
                    }
                }
            } 
            catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }
        else{
            request.setAttribute("message", "Sorry this Servlet only handles file upload request");
        }
		
		request.getRequestDispatcher("/WEB-INF/jsp/Validator.jsp").forward(request, response); 
	}
	
	private List<String> readValidationFile(String path) throws FileNotFoundException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(path));
    	
    	List<String> listOfStrings = new ArrayList<>();
    	
    	String line;
    	while ((line = br.readLine()) != null) 
		{
    		listOfStrings.add(line);
		}

		br.close();
		
		return listOfStrings;
	}
}
