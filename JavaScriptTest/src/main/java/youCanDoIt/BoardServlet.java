package youCanDoIt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 조회의 기능 구현.
		response.setContentType("text/json;charset=utf-8");

		BoardDAO dao = BoardDAO.getInstance();
		List<Board> list = dao.getBoardList();
		System.out.println(list);
		JsonArray ary = new JsonArray();
		for (Board bd : list) {
			JsonObject obj = new JsonObject();
			obj.addProperty("bno", bd.getBno());
			obj.addProperty("title", bd.getTitle());
			obj.addProperty("content", bd.getContent());
			obj.addProperty("writer", bd.getWriter());
			obj.addProperty("creation_date", bd.getCreationDate());
			ary.add(obj);
		}
		Gson gson = new GsonBuilder().create(); 
		String json = gson.toJson(ary); 
		response.getWriter().print(json); 

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 추가, 삭제의 기능을 구현.
		String job = request.getParameter("job");
		if (job.equals("insert")) {

			Board full = new Board();
			full.setBno(request.getParameter("bno"));
			full.setTitle(request.getParameter("title"));
			full.setContent(request.getParameter("content"));
			full.setWriter(request.getParameter("writer"));
			full.setCreationDate(request.getParameter("creationDate"));

			BoardDAO dao = BoardDAO.getInstance();
			if (dao.insertBoard(full)) {
				response.getWriter().print("success");
			} else {
				response.getWriter().print("fail");
			}
		} else if (job.equals("delete")) {
			// 삭제처리
			Board full = new Board();
			full.setBno(request.getParameter("bno"));
			
			
		}

	}

}
