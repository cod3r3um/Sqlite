package co.board.model;

public class Board {
	
	protected int b_id;
	protected String b_title;
	protected String b_content;
	protected String b_writer;
	protected int b_parentid;
	protected String id;
	protected String pw;	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Board() {}
	
	public Board(int b_id, String b_title, String b_content, String b_writer) {
		super();
		this.b_id = b_id;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_writer = b_writer;
	}

	public Board(String b_title, String b_content, String b_writer) {
		super();
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_writer = b_writer;
	}
	
	public Board(int b_id, String b_title, String b_content, String b_writer, int b_parendid) {
		super();
		this.b_id = b_id;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_writer = b_writer;
		this.b_parentid = b_parendid;
	}
	
	public void print() {
		System.out.printf("title_%10s\n content_%20s\n writer_%10s", b_title, b_content, b_writer);
	}

	public int getB_parentid() {
		return b_parentid;
	}

	public void setB_parentid(int b_parendid) {
		this.b_parentid = b_parendid;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_content() {
		return b_content;
	}

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public String getB_writer() {
		return b_writer;
	}

	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}

	@Override
	public String toString() {
		return "- 글번호:" + " " + b_id + 
				"\n - 제목: " + " " + b_title +
				"\n - 내용: " + b_content + 
				"\n - 작성자: " + b_writer +
				"\n - 글번호: " + b_parentid + "]";
	}

}
