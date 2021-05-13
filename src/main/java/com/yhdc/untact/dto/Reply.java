package com.yhdc.untact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reply {
	private int id;
    private String regDate;
    private String updateDate;
    private String relTypeCode;
    private int relId;
    private int memberId;
    private int parentId;
    private String content;
    private boolean blindStatus;
    private String blindDate;
    private boolean delStatus;
    private String delDate;
    private int likeCount;
    private int dislikeCount;

    private String extra__writerName;

    public String getBodyForPrint() {
        String contentForPrint = content.replaceAll("\r\n", "<br>");
        contentForPrint = contentForPrint.replaceAll("\r", "<br>");
        contentForPrint = contentForPrint.replaceAll("\n", "<br>");

        return contentForPrint;
    }
}
