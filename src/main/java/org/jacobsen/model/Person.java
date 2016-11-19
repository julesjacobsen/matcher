package org.jacobsen.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

/**
 * Created by jules on 19/11/2016.
 */
@JsonDeserialize(builder = Person.Builder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Person {

    @JsonProperty
    private String first;
    @JsonProperty
    private String last;

    private Person(Builder builder){
        this.first = builder.first;
        this.last = builder.last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(first, person.first) &&
                Objects.equals(last, person.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }

    @Override
    public String toString() {
        return "Person{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }

    @JsonCreator
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String first = "";
        private String last = "";

        @JsonSetter
        public Builder first(String first) {
            this.first = first;
            return this;
        }

        @JsonSetter
        public Builder last(String last) {
            this.last = last;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

}
