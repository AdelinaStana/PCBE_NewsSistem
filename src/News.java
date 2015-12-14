import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class News implements Serializable{
	
	private long id = System.currentTimeMillis() / 1000L;
	private String domain;
    private String subdomain;
    private String source;
    private String author;
    private Date dateCreated;
    private Date dateModified;
    private String title;
    private String content;
    private boolean deleted = false;
    
 
    public News(String domeniu, String subdomeniu, String sursa,String autor,String titlu , String continut) {
        this.domain = domeniu;
        this.subdomain = subdomeniu;
        this.source = sursa;
        this.author =autor;
        this.dateCreated = new Date();
        this.dateModified = new Date();
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

	public Date getDateCreated() {
		return dateCreated;
	}
	
	public String getDateCreateds() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(dateCreated);
	}	
	
	public void setDateCreated(Date datecreated){
		this.dateCreated = datecreated;
	}
	
	public String getDateModified() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(dateModified);
	}

	public String getContent() {
		return this.content;
	}

	public long getId() {
		return this.id;
	}
}
