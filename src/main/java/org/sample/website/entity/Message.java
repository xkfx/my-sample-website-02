package org.sample.website.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Message {

    private Long id;
    @NotBlank(message = "昵称不能为空")
    @Pattern(regexp = "^[\\w\\u4e00-\\u9fa5]{4,12}$", message = "昵称必须为4到16位中英文")
    private String nickname;
    @NotBlank(message = "内容不能为空")
    @Size(max = 20, message = "内容不能超出20个字符！")
    private String content;

    public Message() {
    }

    public Message(String nickname, String content) {
        this.nickname = nickname;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
