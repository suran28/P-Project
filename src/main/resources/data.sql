-- 회원 테이블에 관리자 계정 1번으로
INSERT INTO member (username, user_id, password, authority)
VALUES ('admin', 'admin0000', '$2a$10$Naqxf0wgmdyA9Q4YHZaeM.Fwwp14ZlN3YOfwY92Gi9v9NQyEQ/yj.', 'ROLE_ADMIN');

-- 회원 테이블에 사용자 테스트 계정 2번으로
INSERT INTO member (username, user_id, password, authority)
VALUES ('test', 'test0000', '$2a$10$Naqxf0wgmdyA9Q4YHZaeM.Fwwp14ZlN3YOfwY92Gi9v9NQyEQ/yj.', 'ROLE_USER');