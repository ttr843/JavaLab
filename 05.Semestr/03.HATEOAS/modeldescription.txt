User 
id
varchar firstname
varchar lastname
int age
varchar country
_____________
Resource
id
varchar name
double rate
varchar country
int year
varchar genre
varchar type
______________
actor
id
varchar firstname
varchar lastname
int age
varchar country
double rate
_______________
subscription
id
int user_id
varchar type
varchar date
_________________
Comment
id
varchar text
int user_id
int resource_id
varchar date
___________
Like 
id
int resource_id
long value


ENUMS:
genre :  Crime,Drama,Fantasy,Action,Adventure,Thriller
Resource_type : film,serial,cartoon
Subscription_type : normal,premium,luxe
