/* Copyright 2016 Google Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.getstarted.objects;

// [START example]
public class Book {
    // [START book]
    private String title;
    private String author;
    private String createdBy;
    private String createdById;
    private String publishedDate;
    private String description;
    private Long id;
    private String imageUrl;
    private double rating;
    private double bufRating;
    private double numberVotes;
    private String comments;
    private String bufComments;

    // [END book]
    // [START keys]
    public static final String AUTHOR = "author";
    public static final String CREATED_BY = "createdBy";
    public static final String CREATED_BY_ID = "createdById";
    public static final String DESCRIPTION = "description";
    public static final String ID = "id";
    public static final String PUBLISHED_DATE = "publishedDate";
    public static final String TITLE = "title";
    public static final String IMAGE_URL = "imageUrl";
    public static final String RATING = "rating";
    public static final String BUFRATING = "bufRating";
    public static final String NBVOTES = "numberVotes";
    public static final String COMMENTS = "comments";
    public static final String BUFCOMMENTS = "bufComments";

    // [END keys]
    // [START constructor]
    // We use a Builder pattern here to simplify and standardize construction of Book objects.
    private Book(Builder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.createdBy = builder.createdBy;
        this.createdById = builder.createdById;
        this.publishedDate = builder.publishedDate;
        this.description = builder.description;
        this.id = builder.id;
        this.imageUrl = builder.imageUrl;
        this.rating = builder.rating;
        this.bufRating = builder.bufRating;
        this.numberVotes = builder.numberVotes;
        this.comments = builder.comments;
        this.bufComments = builder.bufComments;
    }
    // [END constructor]
    // [START builder]
    public static class Builder {
        private String title;
        private String author;
        private String createdBy;
        private String createdById;
        private String publishedDate;
        private String description;
        private Long id;
        private String imageUrl;
        private double rating;
        private double bufRating;
        private double numberVotes;
        private String comments;
        private String bufComments;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder createdById(String createdById) {
            this.createdById = createdById;
            return this;
        }

        public Builder publishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder bufRating(double bufRating) {
            this.bufRating = bufRating;
            return this;
        }

        public Builder numberVotes(double numberVotes) {
            this.numberVotes = numberVotes;
            return this;
        }

        public Builder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public Builder bufComments(String bufComments) {
            this.bufComments = bufComments;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getBufComments() {
        return bufComments;
    }

    public void setBufComments(String bufComments) {
        this.bufComments = bufComments;
    }

    public double getBufRating() {
        return bufRating;
    }

    public void setBufRating(double bufRating) {
        this.rating = bufRating;
    }

    public double getNumberVotes() {
        return numberVotes;
    }

    public void setNumberVotes(double numberVotes) {
        this.numberVotes = numberVotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    // [END builder]
    @Override
    public String toString() {
        return
        "Title: " + title + ", Author: " + author + ", Published date: " + publishedDate
        + ", Added by: " + createdBy;
    }
}
// [END example]
