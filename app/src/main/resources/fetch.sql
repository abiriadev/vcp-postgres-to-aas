select
	tree.path,
	tree.name,
	tree.description,
	tree.attribute,
	tree.category,
	category.attribute_schema,
	category.leaf
from tree
inner join category
on tree.group_id = category.group_id
	and tree.category = category.id_code
where tree.group_id = %d
order by tree.path
limit %d;
