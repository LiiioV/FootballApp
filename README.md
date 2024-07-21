# FootballApp

## 8
### <details> Пусть $\xi_i = 1$, если $i$-ий поймал свой, иначе $0$ </details>
### <details> $$\xi = \sum_{i=1}^{n}\xi_i$$ </details>
### <details> $$E\xi = E\sum_{i=1}^{n}\xi_i = \sum_{i=1}^{n}E\xi_i$$ </details>
### <details> $$\xi_i = P(\text{i поймал свой}) * 1 + 0 = P(\text{i поймал свой}) = \frac{1}{n}$$ </details>
### <details> $$E\xi = E\sum_{i=1}^{n}\xi_i = \sum_{i=1}^{n}\frac{1}{n} = 1$$ </details>

## 9
### <details> $$E\xi = \frac{1}{8}\times 2 + \frac{3}{8} \times (7 + E\xi) + \frac{1}{2}(2 + E\xi) $$ </details>
### <details> $$E\xi = \frac{31}{8} + \frac{7}{8} \times E\xi $$ </details>
### <details> $$E\xi = 31$$ </details>

## 10
### <details> Обозначем ответ с $M_n$ </details>
### <details> Найдем $M_n$ по возростанию рекурсивно по $n$ </details>
### <details> $$M_1 = 1$$ </details>
### <details> $$M_n = M_{n-1}+Е\xi$$, где $\xi$ количесво после чего попался последный </details>
### <details> $$E\xi = \sum_{i=1}^{\infty} i \times P(i\text{-ий последный элемент и до этого его не было}) = \sum_{i=1}^{\infty} i \times \frac{1}{n} (\frac{n-1}{n})^{i-1} = \frac{1}{n} \sum_{i=1}^{\infty} i (\frac{n-1}{n})^{i-1}  $$ </details>
### <details> $$f(x) = \sum_{i = 1}^{\infty} ix^{i-1}  $$ </details>
### <details> $$\int f(x)dx = \int \sum_{i = 1}^{\infty} ix^{i-1}dx =\sum_{i = 1}^{\infty} \int ix^{i-1}dx = \sum_{i = 1}^{\infty} x^i = \frac{x}{1-x} = -1+\frac{1}{1-x} $$ </details>
### <details> $$f(x) = \left(-1+\frac{1}{1-x}\right)' = \frac{1}{(1-x)^2}  $$ </details>
### <details> $$E\xi = \frac{1}{n}\sum_{i = 1}^{\infty} i(\frac{n-1}{n})^{i-1} = \frac{1}{n} f(\frac{n-1}{n}) = n$$ </details>
### <details> $$M_n = M_{n-1}+n$$</details>
### <details> $$M_n = \frac{n(n + 1)}{2}$$</details>
