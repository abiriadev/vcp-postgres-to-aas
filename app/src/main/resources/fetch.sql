select
	tree.id as tree_id,
	tree.dit,
	tree.path,
	tree.name as tree_name,
	tree.description as tree_description,
	tree.alias,
	tree.active,
	tree.category,
	tree.attribute,
	tree.created_by as tree_created_by,
	tree.created_at as tree_created_at,
	tree.modified_by as tree_modified_by,
	tree.modified_at as tree_modified_at,
	tree.group_id,
	category.level,
	category.name as category_name,
	category.description as category_description,
	category.attribute_schema,
	category.created_by as category_created_by,
	category.created_at as category_created_at,
	category.modified_by as category_modified_by,
	category.modified_at as category_modified_at,
	category.leaf,
	category.id as category_id
from tree
inner join category
on tree.group_id = category.group_id
	and tree.category = category.id_code
where tree.group_id = %d
order by tree.path
limit %d;
