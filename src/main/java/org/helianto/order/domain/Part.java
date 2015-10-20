package org.helianto.order.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.def.ActivityState;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.internal.InterpretableCategory;
import org.helianto.document.Documentable;
import org.helianto.document.internal.AbstractDocument;
import org.helianto.user.domain.User;


/**
 * A part.
 * 
 * @author mauriciofernandesdecastro
 */

@javax.persistence.Entity
@Table(name = "ord_part", 
	uniqueConstraints = { @UniqueConstraint(columnNames = {"entityId", "docCode"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("A")

public class Part
	extends AbstractDocument
	implements 
	  Documentable
	, InterpretableCategory
	, Comparable<Part>

{

	private static final long serialVersionUID = 1L;
	
    @ManyToOne
    @JoinColumn(name="categoryId", nullable=true)
    private Category category;
    
    @Transient
    private Integer categoryId;
    
    @Transient
	private String categoryCode;

    @Transient
	private String categoryName;

    @Transient
	private String template;
    
    @Column(length=512)
    private String parsedContent;
    
	private char activityState = 'T';
	
    @ManyToOne
    @JoinColumn(name="currencyId", nullable=true)
    private Currency currency;
    
    @Transient
    private Integer currencyId;
    
    private BigDecimal docValue;
    
    private Boolean docFlag;
    
    @Column(length=4)
    private String tokenPrefix;

	public String getDiscriminator() {
		return "A";
	}
	
	/**
	  * Merger.
	  * 
	  * @param command
	  **/
		public Part merge(Part command) {
			super.merge(command);
			setParsedContent(command.getParsedContent());
			setActivityState(command.getActivityState());
			setDocValue(command.getDocValue());
			setTokenPrefix(command.getTokenPrefix());
			return this;
		}

	/**
	 * Default constructor.
	 */
	public Part() {
		super();
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param docCode
	 */
	public Part(Entity entity, String docCode) {
		this();
		setEntity(entity);
		setDocCode(docCode);
	}
	
	/**
	 * Form constructor.
	 * 
	 * @param user
	 */
	public Part(User user) {
		this(user.getEntity(), "");
		setOwner(user.getIdentity());
	}
	
	/**
	 * Form constructor.
	 * 
	 * @param user
	 * @param docCode
	 */
	public Part(User user, String docCode) {
		this(user.getEntity(), docCode);
		setOwner(user.getIdentity());
	}
	
	/**
	 * Form constructor.
	 *
	 * @param entityId
	 * @param categoryId
	 * @param ownerId
	 * @param currencyId
	 */
	public Part(
		Integer entityId
		, Integer categoryId
		, Integer ownerId
		, Integer currencyId
		) {
		this();
		setEntityId(entityId);
		setCategoryId(categoryId);
		setOwnerId(ownerId);
		setCurrencyId(currencyId);
	}

	/**
	 * Read contructor.
	 * 
	 * @param id
	 * @param entityId
	 * @param categoryId
	 * @param ownerId
	 * @param currencyId
	 * @param issueDate
	 * @param docCode
	 * @param docName
	 * @param docAbstract
	 * @param activityState
	 * @param docFlag
	 * @param docValue
	 * @param tokenPrefix
	 */
	public Part(
			Integer id
			, Integer entityId
			, Integer categoryId
			, Integer ownerId
			, Integer currencyId
			, Date issueDate
			, String docCode
			, String docName
			, String docAbstract
			, Character activityState
			, Boolean docFlag
			, BigDecimal docValue
		    , String tokenPrefix
			) 
	{
		this(entityId, categoryId, ownerId, currencyId);
		setId(id);
		setIssueDate(issueDate);
		setDocCode(docCode);
		setDocName(docName);
		setDocAbstract(docAbstract);
		setActivityState(activityState);
		setDocFlag(docFlag);
		setDocValue(docValue);
		setTokenPrefix(tokenPrefix);
	}
	
	/**
	 * Read contructor.
	 * 
	 * @param id
	 * @param entityId
	 * @param categoryId
	 * @param categoryCode
	 * @param categoryName
	 * @param ownerId
	 * @param currencyId
	 * @param issueDate
	 * @param docCode
	 * @param docName
	 * @param docAbstract
	 * @param activityState
	 * @param docFlag
	 * @param docValue
	 * @param template
	 * @param tokenPrefix
	 */
	public Part(
			Integer id
			, Integer entityId
			, Integer categoryId
			, String categoryCode
			, String categoryName
			, Integer ownerId
			, Integer currencyId
			, Date issueDate
			, String docCode
			, String docName
			, String docAbstract
			, Character activityState
			, Boolean docFlag
			, BigDecimal docValue
		    , String template
		    , String tokenPrefix
			) 
	{
		this(id, entityId, categoryId, ownerId, currencyId, issueDate, docCode, docName, docAbstract
				, activityState, docFlag, docValue, tokenPrefix);
		setCategoryCode(categoryCode); 
		setCategoryName(categoryName);
		setTemplate(template);
	}
			
	/**
	 * Categoria do produto ou serviço.
	 */
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/**
	 * <<Transient>> category id.
	 */
	public Integer getCategoryId() {
		if (getCategory()!=null) {
			return getCategory().getId();
		}
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * <<Transient>> category code.
	 */
	public String getCategoryCode() {
		if (getCategory()!=null) {
			return getCategory().getCategoryCode();
		}
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	/**
	 * <<Transient>> category name.
	 */
	public String getCategoryName() {
		if (getCategory()!=null) {
			return getCategory().getCategoryName();
		}
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * <<Transient>> category script items.
	 */
	public String getTemplate() {
		if (getCategory()!=null) {
			return getCategory().getScriptItems();
		}
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	protected final boolean isCategoryForScript() {
		return getCategory()!=null 
				&& getCategory().getScriptItems()!=null 
				&& getCategory().getScriptItems().trim().length()>0;
	}
	
	@Override
	public String getScriptItems() {
		if (isCategoryForScript()) {
			return getCategory().getScriptItems();
		}
		return "";
	}

	@Override
	public String[] getScriptItemsAsArray() {
		if (isCategoryForScript()) {
			return getCategory().getScriptItemsAsArray();
		}
		return new String[] {};
	}

	@Override
	public List<String> getScriptList() {
		if (isCategoryForScript()) {
			return getCategory().getScriptList();
		}
		return new ArrayList<String>();
	}
	
    public String getParsedContent() {
		return parsedContent;
	}
    public void setParsedContent(String parsedContent) {
		this.parsedContent = parsedContent;
	}
    
    /**
     * Ativ ou inativo, etc.
     */
	public char getActivityState() {
		return activityState;
	}
	public void setActivityState(char activityState) {
		this.activityState = activityState;
	}
	public void setActivityStateAsEnum(ActivityState activityState) {
		setActivityState(activityState.getValue());
	}
	
	/**
	 * Moeda.
	 */
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	/**
	 * <<Traneient>> currency id.
	 */
	public Integer getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	
	public BigDecimal getDocValue() {
		return docValue;
	}
	public void setDocValue(BigDecimal docValue) {
		this.docValue = docValue;
	}
	
	public Boolean isDocFlag() {
		return docFlag;
	}
	public void setDocFlag(Boolean docFlag) {
		this.docFlag = docFlag;
	}
	
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	
	@Override
	public int compareTo(Part other) {
		if (getDocCode()!=null && other!=null) {
			return getDocCode().compareTo(other.getDocCode());
		}
		return 0;
	}

	/**
	 * equals
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Part) {
			return super.equals(other);
		}
		return false;
	}

}
