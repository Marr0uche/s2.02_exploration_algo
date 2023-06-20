# SAE 2.02
Pour cette SAE , nous devions tout d'abord imaginer la structure d'un fichier texte qu'on pouvait lire pour génerer un graphe. 
Ensuite pour la deuxième partie, nous avons eu un fichier texte avec des noeuds, des regions et des arcs. En lisant le fichier, nous avons construit notre propre graphe.
Sur cette SAE, j'étais principalement sur l'IHM. Un camarade dans mon groupe et moi ont fait tout l'ihm en utilisant Graphics2D et nous sommes plutôt fier de ce qu'on a fait, comme le fait qu'on peut sélectionner deux noeuds et ca devient rouge et aussi les différents choses visuels que vous pouvez voir. J'ai touché un peu à la partie métier aussi pour comprendre comment ca marche pour relier notre ihm.

Nous avons fini la SAE avec un oral ou nous avons justifié nos choix.

# Étape 1

• La structure d’un fichier et l’algorithme qui permet de le lire et générer le graphe
  
• Définir comment on pourra choisir une arête qu’on va colorier.

• Faire le diagramme UML de votre projet.

## Contraintes

1. Chaque sommet a une position (x,y) sur un plan 
 
  Pour répondre à cette contrainte, nous avons réfléchis à mettre des attributs a nos noeux, deux précisément. Un pour les 'x' et un autre pour les 'y', compris sur un plan abscisse/ordonnée.

2. Chaque sommet appartient à une région (sous-graphe) et une seule
3. Chaque sommet est relié à d’autres sommets (arêtes),
4. Les arêtes peuvent être valuées et peuvent être colorées,
5. Par la suite, on va colorier les arêtes de façon manuelle, avec des contraintes.

  Pour colorier une arête, nous allons placer sur l'interface deux champs de texte pour préciser le sommet que la personne veut colorier et en quelle couleur.



## Diagramme UML
[Voir le diagramme](https://cdn.discordapp.com/attachments/1115198371846688848/1115554197346193458/image.png)
