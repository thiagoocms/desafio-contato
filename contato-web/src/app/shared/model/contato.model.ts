export interface ListContato {
    letras: ItemListContato[]
    favoritos: SimpleContato[]
}

export interface ItemListContato {
    letra: string
    contatos: Contato[]
}

export interface SimpleContato {
    id: number,
    nome: string,
    email: string,
    celular: string,
    telefone: string,
    snFavorito: boolean
}

export interface Contato {
    id: number,
    nome: string,
    email: string,
    celular: string,
    telefone: string,
    snFavorito: boolean,
    snAtivo: boolean,
    dataCadastro: number
}