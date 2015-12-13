import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class News {
	
	private String domain;
    private String subdomain;
    private String source;
    private String author;
    private Date date;
    private String title;
    private String content;
    
 
    public News(String domeniu, String subdomeniu, String sursa,String autor,String titlu , String continut) {
        this.domain = domeniu;
        this.subdomain = subdomeniu;
        this.source = sursa;
        this.author =autor;
        this.date = new Date();
        this.title = titlu;
        this.content = continut;

    }
 
    public String getDomain() {
        return domain;
    }
 
    public void setDomain(String domeniu) {
        this.domain = domeniu;
    }

	public String getIconName() {
		return  "C:\\Users\\Adelina\\workspace\\NewsSistemPCBE\\Resources\\images";
	}

	public String getTitle() {
		return this.title;
	}

	public String getSubdomain() {
		return this.subdomain;
	}

	public String getSource() {
		return this.source;
	}

	public String getAuthor() {
		return this.author;
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}

	public String getContent() {
		return this.content;
	}
 


}
