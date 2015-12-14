
public class EditorNews extends News {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int views ;

	public EditorNews(String domeniu, String subdomeniu, String sursa,
			String autor, String titlu, String continut) {
		super(domeniu, subdomeniu, sursa, autor, titlu, continut);
		this.views = 0 ;
	}
	
	public void increment()
	{
		this.views = this.views + 1 ;
	}

}
