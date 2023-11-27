select count(id) from users;

select * from users;

select * from book_borrow;

select count(id) from book_borrow
where is_returned = 0;

select name from book_categories;

select b.name,isbn,year, author,bc.name,b.description
from books b join book_categories bc on b.book_category_id = bc.id
                                                           where b.name = 'Clean Code'
                                                           order by isbn desc;

-- find book_id borrowed most
select book_id from book_borrow
group by book_id
order by count(*) desc;

select * from book_categories;

select BC.name, count(book_id)
from book_borrow BBR
join books b on BBR.book_id = b.id
join book_categories BC on b.book_category_id = BC.id
group by name
order by count(*) desc;


select BC.name , count(*)
from book_borrow BBR
join books B on BBR.book_id = B.id
join book_categories BC on B.book_category_id = BC.id
group by name
order by 2 desc;

select isbn,B.name,author,BC.name, year
from books B
join book_categories BC on B.book_category_id = BC.id
where B.name = 'My frist book';

select name from users U
join book_borrow BB on U.id = BB.user_id
join books B on BB.book_id = B.id
where name = 'I love Java_Nancy';


select count(book_id) from book_borrow where is_returned = 0;

-- OR:

select count(*) from book_borrow where returned_date is null;


select name from book_categories;

select b.name, bc.name
from books b inner join book_categories bc on b.book_category_id = bc.id;