package exam.cheater

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ExamController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Exam.list(params), model:[examCount: Exam.count()]
    }

    def show(Exam exam) {
        respond exam
    }

    def create() {
        respond new Exam(params)
    }

    @Transactional
    def save(Exam exam) {
        if (exam == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (exam.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond exam.errors, view:'create'
            return
        }

        exam.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'exam.label', default: 'Exam'), exam.id])
                redirect exam
            }
            '*' { respond exam, [status: CREATED] }
        }
    }

    def edit(Exam exam) {
        respond exam
    }

    @Transactional
    def update(Exam exam) {
        if (exam == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (exam.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond exam.errors, view:'edit'
            return
        }

        exam.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'exam.label', default: 'Exam'), exam.id])
                redirect exam
            }
            '*'{ respond exam, [status: OK] }
        }
    }

    @Transactional
    def delete(Exam exam) {

        if (exam == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        exam.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'exam.label', default: 'Exam'), exam.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'exam.label', default: 'Exam'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
