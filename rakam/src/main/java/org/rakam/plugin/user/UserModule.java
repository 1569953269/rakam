package org.rakam.plugin.user;

import com.google.auto.service.AutoService;
import com.google.common.base.Optional;
import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Scopes;
import com.google.inject.multibindings.Multibinder;
import org.rakam.plugin.SystemEventListener;
import org.rakam.plugin.RakamModule;
import org.rakam.plugin.UserPluginConfig;
import org.rakam.plugin.UserStorage;
import org.rakam.plugin.user.mailbox.UserMailboxStorage;
import org.rakam.server.http.HttpService;
import org.rakam.server.http.WebSocketService;

/**
 * Created by buremba <Burak Emre Kabakcı> on 14/03/15 16:17.
 */
@AutoService(RakamModule.class)
public class UserModule extends RakamModule {
    @Override
    protected void setup(Binder binder) {
        Multibinder<WebSocketService> webSocketServices = Multibinder.newSetBinder(binder, WebSocketService.class);
        webSocketServices.addBinding().to(MailBoxWebSocketService.class).in(Scopes.SINGLETON);

        Multibinder<SystemEventListener> events = Multibinder.newSetBinder(binder, SystemEventListener.class);
        events.addBinding().to(UserStorageListener.class).in(Scopes.SINGLETON);

        Multibinder<HttpService> httpServices = Multibinder.newSetBinder(binder, HttpService.class);
        httpServices.addBinding().to(UserHttpService.class).in(Scopes.SINGLETON);

        UserPluginConfig userPluginConfig = buildConfigObject(UserPluginConfig.class);

        if(userPluginConfig.isMailboxEnabled()) {
            httpServices.addBinding().to(UserMailboxHttpService.class).in(Scopes.SINGLETON);
        }
    }

    @Override
    public String name() {
        return "Customer Analytics Module";
    }

    @Override
    public String description() {
        return "Analyze your users";
    }

    public static class UserStorageListener implements SystemEventListener {

        private final Optional<UserStorage> storage;
        private final Optional<UserMailboxStorage> mailboxStorage;

        @Inject
        public UserStorageListener(com.google.common.base.Optional<UserStorage> storage, com.google.common.base.Optional<UserMailboxStorage> mailboxStorage) {
            this.storage = storage;
            this.mailboxStorage = mailboxStorage;
        }

        @Override
        public void onCreateProject(String project) {
            if(mailboxStorage.isPresent()) {
                mailboxStorage.get().createProject(project);
            }
            if(storage.isPresent()) {
                storage.get().createProject(project);
            }
        }
    }
}
