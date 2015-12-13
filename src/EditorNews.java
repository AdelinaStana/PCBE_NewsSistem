
public class EditorNews extends News {
	private int views ;

	public EditorNews(String domeniu, String subdomeniu, String sursa,
			String autor, String titlu, String continut) {
		super(domeniu, subdomeniu, sursa, autor, titlu, continut);
		views = 0 ;
	}
	
	public void increment()
	{
		views++;
	}

}
