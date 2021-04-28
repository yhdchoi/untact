DROP DATABASE IF EXISTS insta;
CREATE DATABASE insta;
USE insta;

CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '번호',
    regDate DATETIME NOT NULL COMMENT '작성날짜',
    updateDate DATETIME NOT NULL COMMENT '수정날짜',
    boardId INT(10) UNSIGNED NOT NULL COMMENT '게시판번호',
    memberId INT(10) UNSIGNED NOT NULL COMMENT '회원번호',
    title CHAR(100) NOT NULL COMMENT '제목',
    content TEXT NOT NULL COMMENT '본문',
    blindStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '블라인드여부',
    blindDate DATETIME COMMENT '블라인드날짜',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제여부',
    delDate DATETIME COMMENT '삭제날짜',
    hitCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '조회수',
    repliesCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '댓글수',
    likeCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '좋아요수',
    dislikeCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '싫어요수'
);

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 1,
title = '제목1',
content = '본문1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 1,
title = '제목2',
content = '본문2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 1,
title = '제목3',
content = '본문3';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 2,
title = '제목4',
content = '본문4';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 2,
memberId = 2,
title = '제목5',
content = '본문5';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 2,
memberId = 2,
title = '제목6',
content = '본문6';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 2,
memberId = 2,
title = '제목7',
content = '본문7';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 2,
memberId = 2,
title = '제목8',
content = '본문8';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 1,
title = '제목10',
content = '본문10';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 2,
memberId = 1,
title = '제목11',
content = '본문11';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 2,
title = '제목12',
content = '본문12';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 1,
title = '제목',
content = '본문';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 2,
memberId = 2,
title = '제목',
content = '본문';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 3,
title = '제목',
content = '본문';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 2,
memberId = 1,
title = '제목',
content = '본문';



CREATE TABLE board (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '번호',
    regDate DATETIME NOT NULL COMMENT '작성날짜',
    updateDate DATETIME NOT NULL COMMENT '수정날짜',
    `code` CHAR(20) NOT NULL UNIQUE COMMENT '코드',
    `name` CHAR(20) NOT NULL UNIQUE COMMENT '이름',
    blindStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '블라인드여부',
    blindDate DATETIME COMMENT '블라인드날짜',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제여부',
    delDate DATETIME COMMENT '삭제날짜',
    hitCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '조회수',
    repliesCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '댓글수',
    likeCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '좋아요수',
    dislikeCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '싫어요수'
);


INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`name` = 'NOTICE',
`code` = 'notice';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`name` = 'FREE',
`code` = 'free';


CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '번호',
    regDate DATETIME NOT NULL COMMENT '작성날짜',
    updateDate DATETIME NOT NULL COMMENT '수정날짜',
    loginId CHAR(20) NOT NULL UNIQUE COMMENT '로그인아이디',
    loginPw VARCHAR(50) NOT NULL COMMENT '로그인비번',
    `name` CHAR(50) NOT NULL COMMENT '이름',
    nickname CHAR(50) NOT NULL COMMENT '별명',
    email CHAR(50) NOT NULL COMMENT '이메일',
    cellphoneNo CHAR(15) NOT NULL COMMENT '휴대전화번호',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부',
    delDate DATETIME COMMENT '탈퇴날짜'
);

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
`name` = '유저1이름',
nickname = '유저1별명',
email = 'yhdchoi@gmail.com',
cellphoneNo = '01012341234';


INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
`name` = '유저2이름',
nickname = '유저2별명',
email = 'yhdchoi@gmail.com',
cellphoneNo = '01012341234';


INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user3',
loginPw = 'user3',
`name` = '유저3이름',
nickname = '유저3별명',
email = 'yhdchoi@gmail.com',
cellphoneNo = '01012341234';

# 로그인비번 칼럼의 길이를 100으로 늘림
ALTER TABLE member MODIFY COLUMN loginPw VARCHAR(100) NOT NULL;

# 기존 회원의 비밀번호를 암호화 해서 저장
UPDATE member
SET loginPw = SHA2(loginPw, 256);










