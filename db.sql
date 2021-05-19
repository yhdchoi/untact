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
title = '제목9',
content = '본문9';

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



# 부가정보테이블
# 댓글 테이블 추가
CREATE TABLE attr (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `relTypeCode` CHAR(20) NOT NULL,
    `relId` INT(10) UNSIGNED NOT NULL,
    `typeCode` CHAR(30) NOT NULL,
    `type2Code` CHAR(70) NOT NULL,
    `value` TEXT NOT NULL
);

# attr 유니크 인덱스 걸기
## 중복변수 생성금지
## 변수찾는 속도 최적화
ALTER TABLE `attr` ADD UNIQUE INDEX (`relTypeCode`, `relId`, `typeCode`, `type2Code`);

## 특정 조건을 만족하는 회원 또는 게시물(기타 데이터)를 빠르게 찾기 위해서
ALTER TABLE `attr` ADD INDEX (`relTypeCode`, `typeCode`, `type2Code`);

# attr에 만료날짜 추가
ALTER TABLE `attr` ADD COLUMN `expireDate` DATETIME NULL AFTER `value`;


# 댓글 테이블 생성
CREATE TABLE `reply` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '번호',
    regDate DATETIME NOT NULL COMMENT '작성날짜',
    updateDate DATETIME NOT NULL COMMENT '수정날짜',
    relTypeCode CHAR(50) NOT NULL COMMENT '관련 데이터 타입',
    relId INT(10) UNSIGNED NOT NULL COMMENT '관련 데이터 ID',
    parentId INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '부모댓글 ID',
    memberId INT(10) UNSIGNED NOT NULL COMMENT '회원 ID',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제여부',
    delDate DATETIME COMMENT '삭제날짜',
    content TEXT NOT NULL COMMENT '내용',
    blindStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '블라인드여부',
    blindDate DATETIME COMMENT '블라인드날짜',
    hitCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '조회수',
    repliesCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '댓글수',
    likeCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '좋아요수',
    dislikeCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '싫어요수'
);

# 특정 데이터에 관련된 댓글을 읽어는 속도를 빠르게 하기위해
# 인덱스를 건다.
ALTER TABLE `reply` ADD KEY (`relTypeCode`, `relId`);

# 임시로 만들어진 회원은, 비번변경할 필요가 없도록 설정
INSERT INTO attr (
    regDate,
	updateDate,
	relTypeCode,
	relId,
	typeCode,
	type2Code,
	`value`,
	expireDate
	)
SELECT NOW(), NOW(), 'member', id, 'extra', 'needToChangePassword', 0, NULL
FROM `member`;

# 댓글 테스트 데이터 생성
## 1번 댓글 생성
INSERT INTO `reply`
SET regDate = NOW(),
updateDate = NOW(),
relTypeCode = 'article',
relId = 1,
memberId = 1,
content = '댓글1';

## 2번 댓글 생성
INSERT INTO `reply`
SET regDate = NOW(),
updateDate = NOW(),
relTypeCode = 'article',
relId = 1,
memberId = 1,
content = '댓글2';

## 3번 댓글 생성
INSERT INTO `reply`
SET regDate = NOW(),
updateDate = NOW(),
relTypeCode = 'article',
relId = 1,
memberId = 2,
content = '댓글3';

## 4번 댓글 생성
INSERT INTO `reply`
SET regDate = NOW(),
updateDate = NOW(),
relTypeCode = 'article',
relId = 2,
memberId = 2,
content = '댓글4';

# 파일 테이블 추가
CREATE TABLE genFile (
  id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, # 번호
  regDate DATETIME DEFAULT NULL, # 작성날짜
  updateDate DATETIME DEFAULT NULL, # 갱신날짜
  delDate DATETIME DEFAULT NULL, # 삭제날짜
  delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0, # 삭제상태(0:미삭제,1:삭제)
  relTypeCode CHAR(50) NOT NULL, # 관련 데이터 타입(article, member)
  relId INT(10) UNSIGNED NOT NULL, # 관련 데이터 번호
  originFileName VARCHAR(100) NOT NULL, # 업로드 당시의 파일이름
  fileExt CHAR(10) NOT NULL, # 확장자
  typeCode CHAR(20) NOT NULL, # 종류코드 (common)
  type2Code CHAR(20) NOT NULL, # 종류2코드 (attatchment)
  fileSize INT(10) UNSIGNED NOT NULL, # 파일의 사이즈
  fileExtTypeCode CHAR(10) NOT NULL, # 파일규격코드(img, video)
  fileExtType2Code CHAR(10) NOT NULL, # 파일규격2코드(jpg, mp4)
  fileNo SMALLINT(2) UNSIGNED NOT NULL, # 파일번호 (1)
  fileDir CHAR(20) NOT NULL, # 파일이 저장되는 폴더명
  PRIMARY KEY (id),
  KEY relId (relTypeCode,relId,typeCode,type2Code,fileNo)
);

