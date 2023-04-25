DROP DATABASE IF EXISTS sbs_proj_2023;
CREATE DATABASE sbs_proj_2023;
USE sbs_proj_2023;

CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
    );
    
INSERT INTO article
SET regDate = NOW(),
updateDate=NOW(),
title = '제목1',
`body` = '내용1';

SELECT * FROM article;

INSERT INTO article
SET regDate = NOW(),
updateDate=NOW(),
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate=NOW(),
title = '제목3',
`body` = '내용3';

# Member회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    `authLebel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '(3 = 일반, 7 = 관리자)',
    loginPw CHAR(60) NOT NULL,
    `name` CHAR(20) NOT NULL,
    `nickname` CHAR(20) NOT NULL,
    cellphoneNo CHAR(20) NOT NULL,
    email CHAR(50) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '(탈퇴여부)',
    delDate DATETIME COMMENT '탈퇴날짜'
); 

#회원, 테스트 데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
authLebel = 7,
`name` = '관리자',
nickname = '관리자',
cellphoneNo = '01011111111',
email = 'admin@gmail.com';

#회원, 테스트 데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
`name` = '사용자2',
nickname = '사용자2',
cellphoneNo = '01011111111',
email = 'user2@gmail.com';

SELECT * FROM `member`;

# 게시판 테이블 생성
CREATE TABLE board (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `code` CHAR(50) NOT NULL UNIQUE COMMENT 'notice(공지사항), free(자유게시판1), free2(자유게시판2,...',
    `name` CHAR(50) NOT NULL UNIQUE COMMENT '게시판 이름',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제여부(0=탈퇴전, 1=탈퇴)',
    delDate DATETIME COMMENT '삭제날짜'
); 

#기본 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지사항';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free1',
`name` = '자유';

alter table article add column boardId int(10) unsigned not null after `memberId`;

update article
set boardId = 1
where id in(1,2);

update article
set boardId = 2
where id in(3);

#게시물 개수 늘리기
/*insert into article
(
    regDate, updateDate, memberId, boardId, title, `body`
)
select now(), now(), FLOOR(RAND()*2)+1, FLOOR(RAND()*2)+1, concat('제목_',FLOOR(rand())), CONCAT('내용_',FLOOR(RAND()))
from article;*/

ALTER TABLE article
ADD COLUMN hitCount INT(10) UNSIGNED NOT NULL DEFAULT 0;