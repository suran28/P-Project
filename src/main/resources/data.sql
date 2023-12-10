-- 회원 테이블에 관리자 계정 1번으로
INSERT INTO member (username, user_id, password, authority)
VALUES ('admin', 'admin0000', '$2a$10$Naqxf0wgmdyA9Q4YHZaeM.Fwwp14ZlN3YOfwY92Gi9v9NQyEQ/yj.', 'ROLE_ADMIN');

-- 회원 테이블에 사용자 테스트 계정 2번으로
INSERT INTO member (username, user_id, password, authority)
VALUES ('test', 'test0000', '$2a$10$Naqxf0wgmdyA9Q4YHZaeM.Fwwp14ZlN3YOfwY92Gi9v9NQyEQ/yj.', 'ROLE_USER');

-- 대직무분야코드 데이터 집어넣기
-- 빠진거 1, 3, 5, 11
INSERT INTO major_job_code(code, job_name)
VALUES (1,'사업관리'),
       (2,'경영.회계.사무'),
       (3,'금융.보험'),
       (4,'교육.자연.과학.사회과학'),
       (5,'법률.경찰.소방.교도.국방'),
       (6,'보건.의료'),
       (7,'사회복지.종교'),
       (8,'문화.예술.디자인.방송'),
       (9,'운전.운송'),
       (10,'영업.판매'),
       (11,'경비.청소'),
       (12,'이용.숙박.여행.오락.스포츠'),
       (13,'음식서비스'),
       (14,'건설'),
       (15,'광업자원'),
       (16,'기계'),
       (17,'재료'),
       (18,'화학'),
       (19,'섬유.의복'),
       (20,'전기.전자'),
       (21,'정보통신'),
       (22,'식품.가공'),
       (23,'인쇄.목재.가구.공예'),
       (24,'농림어업'),
       (25,'안전관리'),
       (26,'환경.에너지');

-- 게시판 생성
INSERT INTO board(code, board_name)
VALUES (1,'사업관리'),
       (2,'경영.회계.사무'),
       (3,'금융.보험'),
       (4,'교육.자연.과학.사회과학'),
       (5,'법률.경찰.소방.교도.국방'),
       (6,'보건.의료'),
       (7,'사회복지.종교'),
       (8,'문화.예술.디자인.방송'),
       (9,'운전.운송'),
       (10,'영업.판매'),
       (11,'경비.청소'),
       (12,'이용.숙박.여행.오락.스포츠'),
       (13,'음식서비스'),
       (14,'건설'),
       (15,'광업자원'),
       (16,'기계'),
       (17,'재료'),
       (18,'화학'),
       (19,'섬유.의복'),
       (20,'전기.전자'),
       (21,'정보통신'),
       (22,'식품.가공'),
       (23,'인쇄.목재.가구.공예'),
       (24,'농림어업'),
       (25,'안전관리'),
       (26,'환경.에너지');

INSERT INTO article(body, reg_date, title, board_id, member_id)
VALUES ('test용입니다', '20231210', '제목123', 1, 2),
       ('test용입니다123ㄱㄹㅈG', '20231210', 'adghrha', 1, 2),
       ('test용입니다adfhgasdfhareha', '20231210', 'afdhaerhah', 1, 2);

INSERT INTO comment(body, reg_date, article_id, member_id)
VALUES ('어쩔저쩔', '20231210', 1, 2),
       ('test용입니다123ㄱㄹㅈG', '20231210', 1, 2),
       ('test용입니다adfhgasdfhareha', '20231210', 1, 2);