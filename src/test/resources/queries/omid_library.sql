SELECT B.name,BC.name
FROM books B
         INNER JOIN book_categories BC
                    ON B.book_category_id=BC.id;

-- US01 -1
    select  count(id) from users; -- 4891

    select count(distinct id) from users; -- 4891

    select * from users;

SELECT count(*) as borrowedBooks from book_borrow
where is_returned = 0;


SELECT name from book_categories;

select * from books
where name = 'clean code';

select * from books
where name = 'clean code omid';


select bc.name,count(*) from book_borrow bb
join books b on b.id = bb.book_id
join book_categories bc on bc.id = b.book_category_id
group by bc.name
order by 2 desc;

select name from books
where name = 'Head First Java Omid';

select name from users u
join book_borrow bb on u.id = bb.user_id
join books b on bb.book_id = b.id
where name = 'Head First Java Omid';


select * from book_borrow join books b on b.id = book_borrow.book_id
where name = 'The Scrum Field Guide Omid';








