package co.memo.model;

public class Memo {
	String date;
	String title;
	String content;
	
	public Memo() {}
	
	public Memo(String date, String title, String content) {
		super();
		this.date = date;
		this.title = title;
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void print() {
		System.out.println("날짜 : " + this.date);
		System.out.println("제목 : " + this.title);
		System.out.println("내용 : " + this.content);
	}
	
	@Override
	public String toString() {
		return "날짜 : " + date+ "\n제목 : " + title + "\n내용 : " + content ;
	}
	
}
