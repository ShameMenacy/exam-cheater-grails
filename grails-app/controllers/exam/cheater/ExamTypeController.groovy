package exam.cheater

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class ExamTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ExamType.list(params), model:[examTypeCount: ExamType.count()]
    }

    def show(ExamType examType) {
        respond examType
    }

    def create() {
        respond new ExamType(params)
    }

    def save(ExamType examType) {
        if (examType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (examType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond examType.errors, view:'create'
            return
        }

        examType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'examType.label', default: 'ExamType'), examType.id])
                redirect examType
            }
            '*' { respond examType, [status: CREATED] }
        }
    }

    def edit(ExamType examType) {
        respond examType
    }

    def update(ExamType examType) {
        if (examType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (examType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond examType.errors, view:'edit'
            return
        }

        examType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'examType.label', default: 'ExamType'), examType.id])
                redirect examType
            }
            '*'{ respond examType, [status: OK] }
        }
    }

    def delete(ExamType examType) {

        if (examType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        examType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'examType.label', default: 'ExamType'), examType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'examType.label', default: 'ExamType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
