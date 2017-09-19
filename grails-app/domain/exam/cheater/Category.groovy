package exam.cheater

class Category {
    String name
    String description
    Date dateCreated
    Date lastUpdated
    Boolean status

    static hasMany = [questions: Question]

    static constraints = {
        name blank: false, maxSize: 256
        description blank: true, maxSize: 15000
        status defaultValue: Boolean.TRUE
    }

    static mapping = {
        sort 'id'
        order 'desc'
        description sqlType: 'text'
    }
}
