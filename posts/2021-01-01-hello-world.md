---
layout: post
title:  Hello, world!
date:   2019-01-20
author: Ali Sina
summary: This post is just for testing the Markdown. It serves as my handy kramdown Markdown quick-reference.
tags: [kramdown, markdown, reference]
postFooter: Additional information, and maybe a <a href="#">link or two</a>.
---
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis molestie dui, a aliquam augue. Fusce ligula purus, cursus sit amet dui quis, accumsan imperdiet dolor. Nulla pulvinar erat at lorem vehicula volutpat. Sed sollicitudin nulla odio, vitae consectetur dui viverra nec. Mauris dignissim dolor et arcu convallis bibendum. Pellentesque urna velit, tempor vel dolor laoreet, rutrum posuere odio. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In eleifend ac odio in accumsan. Quisque rhoncus tincidunt nisi, non imperdiet sem. Vestibulum sit amet ultrices orci, non facilisis nulla. Donec in purus a justo ultrices dignissim.[^1]

Nullam sit amet laoreet ipsum. Donec in ullamcorper purus. Integer at ante quis neque dignissim rutrum. Curabitur pretium viverra aliquam. Phasellus vel viverra quam. Cras laoreet laoreet metus, in fermentum dui vestibulum ut. Morbi[^2] et lobortis ante. Nunc mauris velit, dignissim non nisl vel, luctus pellentesque nisi. Fusce ullamcorper est.

Cras eget congue erat. In blandit viverra metus nec lacinia.[^3] Aliquam at ante turpis. In ligula massa, tempor quis ullamcorper eget, bibendum vitae risus. Etiam semper quam sem, quis viverra.

This is line is broken into  
two with line breaks. In this case, by a double space.

# H1 Header
## H2 Header
### H3 header
#### H4 header
##### H5 header
###### H6 header

> A sample blockquote.
>
> > Nested blockquote are also possible
>
> ### Headers work too
> This is the outer quote again.

~~~ruby
# This is a Ruby code snippet
class Magic
  def initialize
    @tricks = []
  end
end

magic = Magic.new
begin
  magic.to_json
rescue NoMethodError
  puts "no such method as to_json"
end
~~~

~~~python
# Here is Python
def not_bad(s):
  """Assumes something about the given string
  input: A string
  return: Whatever the output is
  """
  n = s.find('not')      # some comment
  b = s.find('bad')      # another comment
  if n != -1 and b != -1 and b > n:
    s = s[:n] + 'good' + s[b+3:]
  return s
~~~


This below is a horizontal rule.
* * *


1. This is a list item;
2. And another item;
2. And a third one with more text.


* Non numbered item in list.
* A second non-numbered item in list.
* A third `<ul>` list item.


| a simple | table |
| with multiple | lines |

| header 1 | header 2 | header 3 |
|:--------|:--------:|--------:|
| cell 1 | cell 2 | cell 3 |
| cell 4 | cell 3 | cell 6 |


This is *emphasized*, __this__ too!

This w**ork**s as expectd.


An image: ![image](http://placekitten.com/200/200)


Use `Kramdown::Document.new(text).to_html` to convert the
`text` in kramdown syntax to html.


Use backticks to markup code, e.g. `` `code` ``.


* * *
##### FOOTNOTES


[^1]: The definition goes here.
[^2]: The other definition goes here.
[^3]: Some reference to something.
