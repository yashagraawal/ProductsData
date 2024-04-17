package com.test.model;

import java.util.Arrays;

public class Product {
	int id;
	String title;
	float price;
	String description;
	String catagory;
	String image;
	Rating rating;
	
	public Product(){
		
	}
	
	public Product(int id, String title, float price, String description, String catagory, String image,
			Rating rating) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.catagory = catagory;
		this.image = image;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", price=" + price + ", description=" + description
				+ ", catagory=" + catagory + ", image=" + image + ", rating=" + rating + "]";
	}

	public class Rating{
		float rate;
		int count;
		
		public Rating(){
			
		}
		
		public Rating(float rate, int count) {
			super();
			this.rate = rate;
			this.count = count;
		}
		
		public float getRate() {
			return rate;
		}
		public void setRate(int rate) {
			this.rate = rate;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}

		@Override
		public String toString() {
			return "Rating [rate=" + rate + ", count=" + count + "]";
		}
	}
}
