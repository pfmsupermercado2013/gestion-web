package org.back.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.back.constants.BackConstantes;

/**
 *
 * @author Ã“scarJavier
 */
public class SubirArchivosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	       
         
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	        boolean archivoGuardado = false;
                response.setContentType("text/html;charset=iso-8859-15");
                String rutaArchivo = "";
                PrintWriter out = response.getWriter();
                HttpSession session = null;
                session = request.getSession(false);
                String directorio = session.getServletContext().getRealPath(BackConstantes.RUTA_ARCHIVOS_UPLOAD);
                String mensaje_error = "";
	        if (isMultipart) {
                    // Create a factory for disk-based file items
                    FileItemFactory factory = new DiskFileItemFactory();
                    // Create a new file upload handler
                    ServletFileUpload upload = new ServletFileUpload(factory);

	            try {
	            	// Parse the request
	            	List  items = upload.parseRequest(request);
	                Iterator iterator = items.iterator();
	                while (iterator.hasNext()) {
	                    FileItem item = (FileItem) iterator.next();
                            String contentType = request.getServletContext().getMimeType(item.getName());
                            System.out.println("Content-type: "+contentType);
	                    if (!item.isFormField()) {
	                        String fileName = item.getName();	 
	                        File path = new File(directorio);
                                if(contentType != null && 
                                   (contentType.equals("image/png") || 
                                    contentType.equals("image/gif")  ||
                                    contentType.equals("image/jpg")  ||
                                    contentType.equals("image/jpeg"))) {
                                    
                                   if (!path.exists()) {
	                            boolean status = path.mkdirs();
                                   }

                                    File uploadedFile = new File(path + "/" + fileName);

                                    // MÃ¡ximo tamaÃ±o permitido 1 MB
                                    if(item.getSize() < (1024*1000)){
                                        System.out.println(uploadedFile.getAbsolutePath()+" tamaÃ±o: "+uploadedFile.length());
                                        rutaArchivo = BackConstantes.RUTA_ARCHIVOS_UPLOAD+uploadedFile.getName();
                                        item.write(uploadedFile);
                                        session.setAttribute("archivo", uploadedFile);
                                        archivoGuardado = true;
                                    } else {
                                        mensaje_error = "El tamaÃ±o del archivo excede el mÃ¡ximo permitido (1MB).";
                                    }
                                } else {
                                     mensaje_error = "El tipo del archivo no es el permitido (SÃ³lo .png, .gif, .jpeg, .jpg).";
                                }
	                    }
	                }
                        out.println("<!DOCTYPE html>");
                        out.println("<html lang='es'>");
                        out.println("<head>");
                        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-15\">");
                        out.println("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">");
                        out.println("<link href=\"css/styles.css\" rel=\"stylesheet\" media=\"screen\">");
                        out.println("<link href=\"css/datepicker.css\" rel=\"stylesheet\" media=\"screen\">");
                        out.println("<title>Supermercado | Subir Archivos</title>");
                        out.println("<script type='text/javascript' src='js/jquery/jquery_1.4.js'></script>");
                        out.println("<script type='text/javascript' src='js/pop_ups.js'></script>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<fieldset>");
                        out.println("<div class='container'>");
                        if(archivoGuardado){
                            out.println("<legend>Archivo guardado correctamente.</legend>");
                        } else {
                            out.println("<legend>"+mensaje_error+"</legend>");
                        }
                        out.println("<button class='btn btn-large btn-primary' id='btn-cerrar'>Cerrar</button>");
                        out.println("<input type='hidden' id='ruta' ");
                        out.println("value='"+rutaArchivo+"' />");
                        out.println("</div>");
                        out.println("</fieldset>");
                        out.println("</body>");
                        out.println("</html>");    
                    } catch (FileUploadException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        out.close();
                    }
                }
	    }
}
