package mypackage.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;

import java.io.Serializable;

import javax.persistence.CascadeType;

@NoArgsConstructor
@Data

@Entity
@Table(name = "feedback")
//@DynamicInsert
//@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Feedback implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	@Version
	Long versionLock;
	
	@Column(name = "msg")
	String msg;
	
	//@JsonIgnore //can be used to ignore the property in JSON response //can be used only with fields to avoid looping between two entities in findById()
	@OneToOne//(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@MapsId
	@JoinColumn(name = "secret_id", foreignKey = @ForeignKey(name = "FK_SECRET_FEEDBACK"), nullable = false)
	Secret secret;

	/*public Feedback(String msg, Secret secret)
	{
		this.msg = msg;
		this.secret = secret;
	}*/
	
	/*public String toString()
	{
		String info = "Feedback";
		//String info = "Feedback : "+this.msg+" belongs to UserName : "+this.secret.getUn();
		return info;
	}*/
	
	/*public long getId() //getter not needed @Data can be used instead
	{
		return id;
    }
    public void setId(long id) //setter not needed @Data can be used instead
    {
    	this.id = id;
    }

    public String getMsg()
    {
    	return msg;
    }
    public void setMsg(String msg)
    {
    	this.msg = msg;
    }

    public Secret getSecret()
    {
    	return secret;
    }
    public void setSecret(Secret secret)
    {
    	this.secret = secret;
	}*/
}