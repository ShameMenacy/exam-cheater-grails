package exam.cheater

class Answer {
    String content
    String description
    String jaDescription
    String hint
    Boolean isCorrect
    Date dateCreated
    Date lastUpdated

    static hasOne = [question: Question]

    static constraints = {
        content blank: false, maxSize: 15000
        description blank: true, maxSize: 15000
        jaDescription blank: true, maxSize: 15000
        hint blank: true, maxSize: 15000
        isCorrect defaultValue: Boolean.FALSE
    }

    static mapping = {
        sort 'id'
        order 'desc'
        content sqlType: 'text'
        description sqlType: 'text'
        jaDescription sqlType: 'text'
        hint sqlType: 'text'
    }
}
