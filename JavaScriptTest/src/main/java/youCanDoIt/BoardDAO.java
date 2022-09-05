package youCanDoIt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO extends DAO {
	
	private static BoardDAO bd = new BoardDAO();

	private BoardDAO() {

	}

	public static BoardDAO getInstance() {
		return bd;
	}
	
	public List<Board> getBoardList() {
		String sql = "select * from tbl_board";
		List<Board> list = new ArrayList<>();
		conn();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board bd = new Board();
				bd.setBno(rs.getInt("bno"));
				bd.setTitle(rs.getString("title"));
				bd.setContent(rs.getString("content"));
				bd.setWriter(rs.getString("writer"));
				bd.setCreationDate(rs.getString("creation_date"));
				System.out.println(bd);
				list.add(bd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteBoard(int bno) {

		return false;
	}

	public boolean insertBoard(Board board) {
		String sql = "insert into tbl_board (bno, title, content, writer, creation_date) values(?,?,?,?,?)";
		conn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBno());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getWriter());
			pstmt.setString(5, board.getCreationDate());
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}
}
