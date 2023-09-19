-- Insert some sample data for the tables
INSERT INTO ingredients (id, name)
VALUES (UUID_TO_BIN(UUID()), 'flour'),
       (UUID_TO_BIN(UUID()), 'sugar'),
       (UUID_TO_BIN(UUID()), 'butter'),
       (UUID_TO_BIN(UUID()), 'eggs'),
       (UUID_TO_BIN(UUID()), 'milk'),
       (UUID_TO_BIN(UUID()), 'cheese'),
       (UUID_TO_BIN(UUID()), 'tomato'),
       (UUID_TO_BIN(UUID()), 'basil'),
       (UUID_TO_BIN(UUID()), 'salt'),
       (UUID_TO_BIN(UUID()), 'pepper');

INSERT INTO measurements (id, name)
VALUES (UUID_TO_BIN(UUID()), 'cup'),
       (UUID_TO_BIN(UUID()), 'tablespoon'),
       (UUID_TO_BIN(UUID()), 'teaspoon'),
       (UUID_TO_BIN(UUID()), 'gram'),
       (UUID_TO_BIN(UUID()), 'slice');

INSERT INTO quantities (id, quantity, measurement_id)
VALUES
-- flour: 2 cups
(UUID_TO_BIN(UUID()), 2, (SELECT id FROM measurements WHERE name = 'cup')),
-- sugar: 1/4 cup
(UUID_TO_BIN(UUID()), 0.25, (SELECT id FROM measurements WHERE name = 'cup')),
-- butter: 1/2 cup
(UUID_TO_BIN(UUID()), 0.5, (SELECT id FROM measurements WHERE name = 'cup')),
-- eggs: 2
(UUID_TO_BIN(UUID()), 2, NULL),
-- milk: 1 cup
(UUID_TO_BIN(UUID()), 1, (SELECT id FROM measurements WHERE name = 'cup')),
-- cheese: 4 slices
(UUID_TO_BIN(UUID()), 4, (SELECT id FROM measurements WHERE name = 'slice')),
-- tomato: 1
(UUID_TO_BIN(UUID()), 1, NULL),
-- basil: 2 tablespoons
(UUID_TO_BIN(UUID()), 2, (SELECT id FROM measurements WHERE name = 'tablespoon')),
-- salt: 1/2 teaspoon
(UUID_TO_BIN(UUID()), 0.5, (SELECT id FROM measurements WHERE name = 'teaspoon')),
-- pepper: 1/4 teaspoon
(UUID_TO_BIN(UUID()), 0.25, (SELECT id FROM measurements WHERE name = 'teaspoon'));

INSERT INTO recipes (id, title, description)
VALUES
-- Cake recipe
(UUID_TO_BIN(UUID()), 'Cake', 'A simple and delicious cake recipe that is easy to make and enjoy.'),
-- Cheese toast recipe
(UUID_TO_BIN(UUID()), 'Cheese Toast', 'A quick and tasty snack that is perfect for breakfast or lunch.');

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity_id)
VALUES
-- Cake ingredients
((SELECT id FROM recipes WHERE title = 'Cake'),
 (SELECT id FROM ingredients WHERE name = 'flour'), (SELECT id
                                                                  FROM quantities
                                                                  WHERE quantity = 2
                                                                    AND measurement_id =
                                                                        (SELECT id FROM measurements WHERE name = 'cup'))),
((SELECT id FROM recipes WHERE title = 'Cake'),
 (SELECT id FROM ingredients WHERE name = 'sugar'), (SELECT id
                                                                  FROM quantities
                                                                  WHERE quantity = 0.25
                                                                    AND measurement_id =
                                                                        (SELECT id FROM measurements WHERE name = 'cup'))),
((SELECT id FROM recipes WHERE title = 'Cake'),
 (SELECT id FROM ingredients WHERE name = 'butter'), (SELECT id
                                                                   FROM quantities
                                                                   WHERE quantity = 0.5
                                                                     AND measurement_id =
                                                                         (SELECT id FROM measurements WHERE name = 'cup'))),
((SELECT id FROM recipes WHERE title = 'Cake'),
 (SELECT id FROM ingredients WHERE name = 'eggs'),
 (SELECT id FROM quantities WHERE quantity = 2 AND measurement_id IS NULL)),
((SELECT id FROM recipes WHERE title = 'Cake'),
 (SELECT id FROM ingredients WHERE name = 'milk'), (SELECT id
                                                                 FROM quantities
                                                                 WHERE quantity = 1
                                                                   AND measurement_id =
                                                                       (SELECT id FROM measurements WHERE name = 'cup'))),
-- Cheese toast ingredients
((SELECT id FROM recipes WHERE title = 'Cheese Toast'),
 (SELECT id FROM ingredients WHERE name = 'cheese'), (SELECT id
                                                                   FROM quantities
                                                                   WHERE quantity = 4
                                                                     AND measurement_id =
                                                                         (SELECT id FROM measurements WHERE name = 'slice'))),
((SELECT id FROM recipes WHERE title = 'Cheese Toast'),
 (SELECT id FROM ingredients WHERE name = 'tomato'),
 (SELECT id FROM quantities WHERE quantity = 1 AND measurement_id IS NULL)),
((SELECT id FROM recipes WHERE title = 'Cheese Toast'),
 (SELECT id FROM ingredients WHERE name = 'basil'), (SELECT id
                                                                  FROM quantities
                                                                  WHERE quantity = 2
                                                                    AND measurement_id =
                                                                        (SELECT id FROM measurements WHERE name = 'tablespoon'))),
((SELECT id FROM recipes WHERE title = 'Cheese Toast'),
 (SELECT id FROM ingredients WHERE name = 'salt'), (SELECT id
                                                                 FROM quantities
                                                                 WHERE quantity = 0.5
                                                                   AND measurement_id =
                                                                       (SELECT id FROM measurements WHERE name = 'teaspoon'))),
((SELECT id FROM recipes WHERE title = 'Cheese Toast'),
 (SELECT id FROM ingredients WHERE name = 'pepper'), (SELECT id
                                                                   FROM quantities
                                                                   WHERE quantity = 0.25
                                                                     AND measurement_id =
                                                                         (SELECT id FROM measurements WHERE name = 'teaspoon')));
